var sum = 0;

for (var i = 0; i < 100; i++) {
    var line = parseInt(readline());
    while (line > 0) {
        line = Math.floor(line / 3) - 2;
        sum -= (line > 0) ? -line : 0;
    }
}

print(sum);
