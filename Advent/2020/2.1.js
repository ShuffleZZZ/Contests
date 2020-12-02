const input = document.getElementsByTagName('pre')[0].textContent.split('\n');

const parsed = input.map(el => {
    let [nums, letter, word] = el.split(' ');
    nums = nums.split('-');
    letter = (letter || '')[0];
    return [nums, letter, word];
});

parsed.splice(parsed.length - 1);

let cnt = 0;

parsed.forEach(([range, letter, word]) => {
    let entries = word.split(letter).length - 1;
    if (entries >= range[0] && entries <= range[1]) {
        ++cnt;
    }
});

console.log(cnt);
