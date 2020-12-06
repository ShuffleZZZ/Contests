let res = 0;

const input = document.getElementsByTagName('pre')[0].textContent
.split('\n\n')
.map(it => it.split('\n'));
input[input.length - 1].pop();

input.forEach(group => {
  let answers = new Set(group[0]);
  group.shift();

  group.forEach(person => {
    let sameAnswers = new Set();

    [...person].forEach(it => {
      if (answers.has(it)) sameAnswers.add(it);
    });

    answers = new Set(sameAnswers);
  });

  res += answers.size;
});

console.log(res);
