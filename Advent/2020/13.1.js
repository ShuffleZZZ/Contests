const [timestamp, busses] = document.getElementsByTagName('pre')[0].textContent
  .split('\n').slice(0,2);
const stamps =
  busses.split(',').filter(it => it !== 'x').map(Number).map(buss => {
    if (buss < timestamp) return [buss * (Math.floor(timestamp / buss) + 1) - timestamp, buss];
    return [buss, buss];
  });

console.log(
  stamps.find(it =>
    it.includes(Math.min(...stamps.map(it => it[0])))
  ).reduce((a, b) => a * b, 1)
);
