const input = document.getElementsByTagName('pre')[0].textContent.split('\n').map(line => line.split(' '));
input.pop();

const ops = ['jmp', 'nop'];
const permutation = [];

input.forEach(([op, value], ind) => {
  if (ops.includes(op)) {
    const newPermutation = [...input];
    newPermutation[ind] = [ops[op === 'jmp' ? 1 : 0], value];
    permutation.push(newPermutation);
  }
});

for (let change of permutation) {
  let acc = 0;
  const commands = new Set();
  let i = 0;

  for (; i <= change.length; i++) {
    if (i === change.length) break;

    const [op, value] = change[i];
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
    }
  }

  if (i === change.length) {
    console.log(acc);
    break;
  }
}
