const input = document.getElementsByTagName('pre')[0].textContent.split('\n');

input.forEach(el1 => {
    input.forEach(el2 => {
        if (parseInt(el1) + parseInt(el2) === 2020) {
            console.log(el1 * el2);
        }
    })
});
