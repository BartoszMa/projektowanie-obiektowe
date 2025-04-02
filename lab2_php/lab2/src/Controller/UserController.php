<?php

namespace App\Controller;

use App\Entity\User;
use App\Repository\UserRepository;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\Routing\Annotation\Route;


#[Route('/user', name: 'api_user_')]
class UserController extends AbstractController
{
    #[Route('/', name: 'list', methods: ['GET'])]
    public function index(UserRepository $userRepository): JsonResponse
    {
        $users = $userRepository->findAll();

        $data = array_map(fn(User $user) => [
            'id' => $user->getId(),
            'name' => $user->getName(),
        ], $users);

        return $this->json($data);
    }

    #[Route('/create', name: 'create', methods: ['POST'])]
    public function create(Request $request, EntityManagerInterface $entityManager): JsonResponse
    {
        $data = json_decode($request->getContent(), true);

        if (!isset($data['name'])) {
            return $this->json(['error' => 'Invalid data'], 400);
        }

        $user = new User();
        $user->setName($data['name']);

        $entityManager->persist($user);
        $entityManager->flush();

        return $this->json(['message' => 'Category created', 'id' => $user->getId()], 201);
    }
}
