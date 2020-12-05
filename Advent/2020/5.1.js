const ROWS = 127;
const COLUMNS = 7;
const SHIFT = 8;

let max = 0;

const input = document.getElementsByTagName('pre')[0].textContent.split('\n');
input.pop();

input.forEach(code => {
  let [F, B, L, R] = [0, ROWS, 0, COLUMNS];

  for (let symbol of code) {
    switch (symbol) {
      case 'F':
        B = F + ((B - F) >> 1);
        break;
      case 'B':
        F += ((B - F) >> 1) + 1;
        break;
      case 'L':
        R = L + ((R - L) >> 1);
        break;
      case 'R':
        L += ((R - L) >> 1) + 1;
        break;
      default:
        console.log('wrong input');
    }
  }

  if (F !== B || L !== R) console.log(`failed to calculate a seat for ${code}`)

  max = Math.max(max, SHIFT * F + R);
});


console.log(max);
