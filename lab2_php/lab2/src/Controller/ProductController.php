<?php

namespace App\Controller;

use App\Entity\Product;
use App\Repository\ProductRepository;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\Routing\Annotation\Route;

#[Route('/product', name: 'api_product_')]
class ProductController extends AbstractController
{
    #[Route('/', name: 'app_product')]
    public function index(ProductRepository $productRepository): JsonResponse
    {
        $products = $productRepository->findAll();

        $data = array_map(fn(Product $product) => [
            'id' => $product->getId(),
            'name' => $product->getName(),
            'price' => $product->getPrice(),
        ], $products);

        return $this->json($data);
    }

    #[Route('/create', name: 'create', methods: ['POST'])]
    public function create(Request $request, EntityManagerInterface $entityManager): JsonResponse
    {
        $data = json_decode($request->getContent(), true);

        if (!isset($data['name']) || !isset($data['price'])) {
            return $this->json(['error' => 'Invalid data'], 400);
        }

        $product = new Product();
        $product->setName($data['name']);
        $product->setPrice($data['price']);

        $entityManager->persist($product);
        $entityManager->flush();

        return $this->json(['message' => 'Product created', 'id' => $product->getId()], 201);
    }

    #[Route('/edit/{id}', name: 'edit', methods: ['PUT', 'PATCH'])]
    public function edit(Product $product, Request $request, EntityManagerInterface $entityManager): JsonResponse
    {
        $data = json_decode($request->getContent(), true);

        if (isset($data['name'])) {
            $product->setName($data['name']);
        }
        if (isset($data['price'])) {
            $product->setPrice($data['price']);
        }

        $entityManager->flush();

        return $this->json(['message' => 'Product updated']);
    }

    #[Route('/delete/{id}', name: 'delete', methods: ['DELETE'])]
    public function delete(Product $product, EntityManagerInterface $entityManager): JsonResponse
    {
        $entityManager->remove($product);
        $entityManager->flush();

        return $this->json(['message' => 'Product deleted']);
    }
}
