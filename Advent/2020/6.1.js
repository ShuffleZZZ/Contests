let res = 0;

document.getElementsByTagName('pre')[0].textContent
.split('\n\n')
.map(it => it.replaceAll('\n', ''))
.forEach(group => {
  let answers = new Set();

  for (let answer of group) answers.add(answer);

  res += answers.size;
});

console.log(res);
