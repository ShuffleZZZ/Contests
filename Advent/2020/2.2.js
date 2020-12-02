const input = document.getElementsByTagName('pre')[0].textContent.split('\n');

const parsed = input.map(el => {
    let [nums, letter, word] = el.split(' ');
    nums = nums.split('-').map(Number).map(it => it - 1);
    letter = (letter || '')[0];
    return [nums, letter, word];
});

parsed.splice(parsed.length - 1);

let cnt = 0;

parsed.forEach(([range, letter, word]) => {
    const oldCnt = cnt;
    range.forEach(border => {
        if (word[border] === letter) ++cnt;
    })

    if (cnt - oldCnt === 2) cnt = oldCnt;
});

console.log(cnt);
