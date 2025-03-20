program Rand;

var
    data: array[0..49] of Integer;

procedure randNum();
var
    i: Integer;
begin

    for i := 0 to 49 do
        data[i] := random(100);

    for i := 0 to 49 do
        writeln(data[i]);
end;

begin
    randNum;
end.
