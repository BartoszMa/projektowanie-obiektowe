program Rand;

var
    data: array[0..49] of Integer;

procedure randNum();
var
    i: Integer;
begin

    for i := 0 to 49 do
        data[i] := random(100);
end;

procedure sortNum();
var
    i, j, tmp: Integer;
begin
    for i := 0 to 49 do
        for j := 0 to 49 - i - 1 do
        begin
            if data[j] > data[j+1] then
            begin
                 tmp := data[j];
                 data[j] := data[j+1];
                 data[j+1] := tmp;
            end;
        end;
end;

var
    q: Integer;

begin
    randNum;

    writeln('Before sort');

    for q := 0 to 49 do
        writeln(data[q]);

    writeln('After sort');

    sortNum;

    for q := 0 to 49 do
        writeln(data[q]);
end.
