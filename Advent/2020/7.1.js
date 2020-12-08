const input = document.getElementsByTagName('pre')[0].textContent
  .split('.\n').map(it => it.split('bags contain'));
input.pop();

const colors = new Set();
let prevLayer = new Set(['shiny gold']);

while (true) {
  const dfsLayer = new Set();

  prevLayer.forEach(color => {
    input.forEach(([bag, inside]) => {
      if (inside.includes(color)) dfsLayer.add(bag);
    });
  });

  if (dfsLayer.size === 0) break;

  dfsLayer.forEach(color => colors.add(color));
  prevLayer = new Set(dfsLayer);
}

console.log(colors.size);
