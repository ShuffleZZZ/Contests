const input = document.getElementsByTagName('pre')[0].textContent
  .split('\n').map(Number);
input.pop();

const preamble = input.slice(0, 25);
const rest = input.slice(25);

let invalid = 0;

for (let i = 0; i < rest.length; i++) {
  let isFound = false;

  for (const term of preamble) {
    const term2 = rest[i] - term;

    if (preamble.includes(term2)) {
      preamble.shift();
      preamble.push(rest.shift());
      --i;
      isFound = true;
      break;
    }
  }

  if(!isFound) {
    invalid = rest[i];
    break;
  }
}

let i = 0;
let j = 1;
let sum = input[i] + input[j];

while (sum !== invalid && j < input.length) {
  if (sum < invalid) {
    sum += input[++j];
  }

  if (sum > invalid) {
    sum -= input[i++];
  }

  if (i === j) sum += input[++j];
}

if (j === input.length) console.log("Error");

const range = input.slice(i, j);
console.log(Math.max(...range) + Math.min(...range));

