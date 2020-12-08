class Node {
  constructor(weight) {
    this.weight = weight;
  }

  addNodes(string) {
    const args = string.split(', ');
    args[args.length - 1].slice(0, -1);

    this.nodes = args.map(it => Node(it => it.split(' ')[0]));
  }
}

const input = document.getElementsByTagName('pre')[0].textContent
  .split('.\n').map(it => it.split(' bags contain '));
input.pop();

const amount = new Set();
const root = Node(1);
let prevLayer = new Set(['shiny gold']);

while (true) {
  const dfsLayer = new Set();

  prevLayer.forEach(color => {
    input.forEach(([bag, inside]) => {
      if (bag.includes(color)) {

      }
        dfsLayer.add(bag);
    });
  });

  if (dfsLayer.size === 0) break;

  dfsLayer.forEach(color => colors.add(color));
  prevLayer = new Set(dfsLayer);
}

console.log(colors.size);
