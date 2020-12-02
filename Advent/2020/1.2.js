const input = document.getElementsByTagName('pre')[0].textContent.split('\n');

input.forEach(el1 => {
    input.forEach(el2 => {
        input.forEach(el3 => {
            if (parseInt(el1) + parseInt(el2) + parseInt(el3) === 2020) {
                console.log(el1 * el2 * el3);
            }
        })
    })
});
