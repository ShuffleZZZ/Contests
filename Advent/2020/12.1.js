const FULL_CIRCLE = 360;
const DIRECTIONS = [0, 90, 180, 270];
const input = document.getElementsByTagName('pre')[0].textContent
  .split('\n').map(it => {
    const [full, letter, dist] = /([A-Z])(\d+)/.exec(it) || [];
    return [letter, Number(dist)];
  });
input.pop();

move = (degrees, value) => {
  switch (degrees) {
    case DIRECTIONS[0]:
      xShift += value;
      break;
    case DIRECTIONS[1]:
      yShift += value;
      break;
    case DIRECTIONS[2]:
      xShift -= value;
      break;
    case DIRECTIONS[3]:
      yShift -= value;
      break;
    default:
      console.log('Unable to move');
  }
};

let curDirection = 0;
let xShift = 0;
let yShift = 0;

input.forEach(([direction, value]) => {
  switch (direction) {
    case 'E':
      move(DIRECTIONS[0], value);
      break;
    case 'N':
      move(DIRECTIONS[1], value);
      break;
    case 'W':
      move(DIRECTIONS[2], value);
      break;
    case 'S':
      move(DIRECTIONS[3], value);
      break;
    case 'L':
      curDirection += value;
      break;
    case 'R':
      curDirection -= value;
      break;
    case 'F':
      move(curDirection, value);
      break;
    default:
      console.log('Incorrect input');
  }

  curDirection = (curDirection + FULL_CIRCLE) % FULL_CIRCLE;

  if (!DIRECTIONS.includes(curDirection)) {
    console.log(`Invalid direction ${curDirection}`);
  }
});

console.log(Math.abs(xShift) + Math.abs(yShift));
