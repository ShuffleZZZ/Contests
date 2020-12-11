const input = document.getElementsByTagName('pre')[0].textContent
  .split('\n').map(it => [...it]);
input.pop();

let oldInput;

updateSeat = (i, j) => {
  switch (oldInput[i][j]) {
    case 'L':
      if (!neighbours(i, j)) input[i][j] = '#';
      break;
    case '#':
      if (neighbours(i, j) >= 5) input[i][j] = 'L';
      break;
  }
};

neighbours = (i, j) => {
  let res = 0;

  for (let k = -1; k <= 1; ++k) {
    for (let l = -1; l <= 1; ++l) {
      res += move(i, j, k, l);
    }
  }

  return res;
}

move = (row, column, k, l) => {
  if (k === 0 && l === 0) return 0;

  let i = row + k;
  let j = column + l;
  while(i < input.length && i >= 0 && j < input[0].length && j >= 0) {
    if (oldInput[i][j] === '#') return 1;
    if (oldInput[i][j] === 'L') return 0;

    i += k;
    j += l;
  }

  return 0;
}

let isSame = false;
let cnt = 0;

while (!isSame) {
  oldInput = input.map(it => [...it]);
  for (let i = 0; i < input.length; ++i) {
    for (let j = 0; j < input[i].length; ++j) {
      updateSeat(i, j);
    }
  }

  isSame = true;
  for (let i = 0; i < input.length; ++i) {
    for (let j = 0; j < input[i].length; ++j) {
      if (oldInput[i][j] !== input[i][j]) isSame = false;
    }
  }
}

let res = 0;

for (let i = 0; i < input.length; ++i) {
  for (let j = 0; j < input[i].length; ++j) {
    if (oldInput[i][j] === '#') ++res;
  }
}

console.log(res);
