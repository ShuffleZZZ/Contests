const input = document.getElementsByTagName('pre')[0].textContent.split('\n');

let x = 0;
let y = 0;

let treeCnt = 0;

while (true) {
  x = (x + 3) % input[0].length;
  y += 1;

  if (y === input.length) {
    console.log(treeCnt);
    break;
  }

  if (input[y][x] === '#') {
    ++treeCnt;
  }
}
