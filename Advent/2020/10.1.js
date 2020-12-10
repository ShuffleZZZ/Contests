const input = document.getElementsByTagName('pre')[0].textContent
  .split('\n').map(Number);
input.pop();

let diff = [0, 0, 0, 1];

input.sort((a, b) => a - b).forEach(adapter => {
  ++diff[adapter - diff[0]];
  diff[0] = adapter;
});

console.log(diff[1] * diff[3]);
