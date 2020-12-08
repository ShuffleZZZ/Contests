const input = document.getElementsByTagName('pre')[0].textContent.split('\n').map(line => line.split(' '));
input.pop();

let acc = 0;
const commands = new Set();

for (let i = 0; i < input.length; i++) {
  const [op, value] = input[i];
  const intValue = parseInt(value);

  if (commands.has(i)) break;
  commands.add(i);

  switch (op) {
    case 'acc':
      acc += intValue;
      break;

    case 'jmp':
      i += intValue - 1;
      break;

    case 'nop':
      break;

    default:
      console.log("Incorrect operation")
  }
}

console.log(acc);
