let options = [
    set0 = ['Option 1','Option 2'],
    set1 = ['First Option','Second Option','Third Option']
];

function makeUL(array) {
    // Create the list element:
    let list = document.createElement('ul');

    for(let i = 0; i < array.length; i++) {
        // Create the list item:
        let item = document.createElement('li');

        // Set its contents:
        item.appendChild(document.createTextNode(array[i]));

        // Add it to the list:
        list.appendChild(item);
    }

    // Finally, return the constructed list:
    return list;
}

// Add the contents of options[0] to #generator:
document.getElementById('admin-generator').appendChild(makeUL(options[0]));