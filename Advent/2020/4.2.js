let validPassports = 0;
const fields    = ['byr', 'iyr', 'eyr', 'hgt', 'hcl', 'ecl', 'pid'];
const eyeColors = ['amb', 'blu', 'brn', 'gry', 'grn', 'hzl', 'oth'];

const regexps = {
  byr: (value) => {
    return !isNaN(value) && value >= 1920 && value <= 2002;
  },
  iyr: (value) => {
    return !isNaN(value) && value >= 2010 && value <= 2020;
  },
  eyr: (value) => {
    return !isNaN(value) && value >= 2020 && value <= 2030;
  },
  hgt: (value) => {
    const num = value.slice(0, value.length - 2);
    const metric = value.slice(-2);

    if (isNaN(num)) return false;
    if (metric !== 'cm' && metric !== 'in') return false;

    if (metric === 'cm') return num >= 150 && num <= 193;
    if (metric === 'in') return num >= 59 && num <= 76;
  },
  hcl: (value) => {
    return /^#[a-f0-9]{6}$/i.test(value);
  },
  ecl: (value) => {
    return eyeColors.includes(value);
  },
  pid: (value) => {
    return /^[0-9]{9}$/.test(value);
  }
}

const input = document.getElementsByTagName('pre')[0].textContent.split('\n\n')
  .map(it => it.replaceAll(`\n`, ' ').split(' '))
  .forEach(pass => {
    const set = new Set();

    pass.forEach(entry => {
      const [field, value] = entry.split(':');
      // console.log((regexps[field] || String)(value), value);
      if (fields.includes(field) && regexps[field](value)) set.add(field);
    });

    if (set.size === fields.length) ++validPassports;
  });

console.log(validPassports);
