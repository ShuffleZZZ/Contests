const ROWS = 127;
const COLUMNS = 7;
const SHIFT = 8;

let seats = [];
seats.fill(false, 0, SHIFT * ROWS + COLUMNS);

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

  if (F !== B || L !== R) console.log(`failed to calculate a seat for ${code}`);

  seats[SHIFT * F + R] = true;
});

for (let i = 1; i < seats.length - 1; i++) {
  if (!seats[i] && seats[i - 1] && seats[i + 1]) console.log(i);
}
