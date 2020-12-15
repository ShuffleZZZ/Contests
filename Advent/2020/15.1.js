const LAST_STEP = 2020;

const input = [14,8,16,0,1,17];
const map = new Map(input.map((num, ind) => [num, [ind]]));

let last = input[input.length - 1];

for (let i = input.length; i < LAST_STEP; i++) {
  const indices = map.get(last) || [];
  const diff = indices.length <= 1 ? 0 : indices[1] - indices[0];

  const el = map.get(diff) || [];
  while (el.length >= 2) el.shift();
  el.push(i);

  map.set(diff, el);
  last = diff;
}

console.log(last);
