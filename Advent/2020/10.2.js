const JOLTS_GAP = 3;

const input = document.getElementsByTagName('pre')[0].textContent
  .split('\n').map(Number);
input.pop();
input.sort((a, b) => a - b);
input.push(input[input.length - 1] + JOLTS_GAP);

const res = Array(input[input.length - 2]);
res.fill(0, 0, res.length);

function count(curJolts) {
  let sum = 0;

  for(let fit = curJolts + 1; fit - curJolts <= JOLTS_GAP; fit++) {
    if (fit === input[input.length - 1]) return 1;

    if (input.includes(fit)) sum += res[fit] || count(fit);
  }

  return res[curJolts] = sum;
}

console.log(count(0));
