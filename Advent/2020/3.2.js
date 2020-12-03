const input = document.getElementsByTagName('pre')[0].textContent.split('\n');

const shifts = [[1, 1], [3, 1], [5, 1], [7, 1], [1, 2]];
const treeCnt = [];

for (const shift of shifts) {
  let x = 0;
  let y = 0;

  treeCnt.push(0);

  while (true) {
    x = (x + shift[0]) % input[0].length;
    y += shift[1];

    if (y >= input.length) break;

    if (input[y][x] === '#') ++treeCnt[treeCnt.length - 1];
  }
}

console.log(treeCnt.reduce((a, b) => a * b, 1));
