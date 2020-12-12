const FULL_CIRCLE = 360;
const DIRECTIONS = [0, 90, 180, 270];

const input = document.getElementsByTagName('pre')[0].textContent
  .split('\n').map(it => {
    const [full, letter, dist] = /([A-Z])(\d+)/.exec(it) || [];
    return [letter, Number(dist)];
  });
input.pop();

rotate = (degrees) => {
  switch (degrees) {
    case DIRECTIONS[1]:
      [xWaypoint, yWaypoint] = [yWaypoint, -xWaypoint];
      break;
    case DIRECTIONS[2]:
      [xWaypoint, yWaypoint] = [-xWaypoint, -yWaypoint];
      break;
    case DIRECTIONS[3]:
      [xWaypoint, yWaypoint] = [-yWaypoint, xWaypoint];
      break;
    default:
      console.log(`Unable to move ${degrees} degrees`);
  }
};

let xShift = 0;
let yShift = 0;
let xWaypoint = 10;
let yWaypoint = 1;

input.forEach(([direction, value]) => {
  switch (direction) {
    case 'E':
      xWaypoint += value;
      break;
    case 'N':
      yWaypoint += value;
      break;
    case 'W':
      xWaypoint -= value;
      break;
    case 'S':
      yWaypoint -= value;
      break;
    case 'L':
      rotate(FULL_CIRCLE - value);
      break;
    case 'R':
      rotate(value);
      break;
    case 'F':
      xShift += xWaypoint * value;
      yShift += yWaypoint * value;
      break;
    default:
      console.log('Incorrect input');
  }
});

console.log(Math.abs(xShift) + Math.abs(yShift));
