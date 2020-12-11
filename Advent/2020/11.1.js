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
      if (neighbours(i, j) >= 4) input[i][j] = 'L';
      break;
  }
};

neighbours = (i, j) => {
  let res = 0;

  const leftBoard = Math.max(i - 1, 0);
  const rightBoard = Math.min(i + 1, input.length - 1);
  const upperBoard = Math.max(j - 1, 0);
  const bottomBoard = Math.min(j + 1, input[0].length - 1);

  for (let k = leftBoard; k <= rightBoard; ++k) {
    for (let l = upperBoard; l <= bottomBoard; ++l) {
      if ((k !== i || l !== j) && oldInput[k][l] === '#') ++res;
    }
  }

  return res;
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
