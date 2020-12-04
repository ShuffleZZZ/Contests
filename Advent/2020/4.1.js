let validPassports = 0;
const fields = ['byr', 'iyr', 'eyr', 'hgt', 'hcl', 'ecl', 'pid'];

const input = document.getElementsByTagName('pre')[0].textContent.split('\n\n')
  .map(it => it.replaceAll(`\n`, ' ').split(' '))
  .forEach(pass => {
    const set = new Set();

    pass.forEach(entry => {
      const field = entry.split(':')[0];
      if (fields.includes(field) && !set.has(field)) {
        set.add(field);
      }
    });

    if (set.size === fields.length) {
      ++validPassports;
    }
  });

console.log(validPassports);
