const input = document.getElementsByTagName('pre')[0].textContent
  .split('\n').map(Number);
input.pop();

const preamble = input.slice(0, 25);
const rest = input.slice(25);

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
    console.log(rest[i]);
    break;
  }
}
