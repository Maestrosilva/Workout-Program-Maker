let muscleGroupVisible = false;
let categoryVisible = false;
let menuClicks = 0;
let day = 'MONDAY';
var collection = [];


function closeAllLogs() {
    document.getElementsByClassName('create-workout-log').item(0).style.visibility = 'hidden';
    document.getElementById('exercises-section').style.visibility = 'hidden';
    document.getElementById('category-section').style.visibility = 'hidden';
    document.getElementById('muscle-group-section').style.visibility = 'hidden';
    let el = document.getElementsByClassName('show-section')
        .item(0);
    el.style.display = 'none';
}

function closeExercisesLog() {
    document.getElementById('exercises-section').style.visibility = 'hidden';
    document.getElementById('category-section').style.visibility = 'hidden';
    document.getElementById('muscle-group-section').style.visibility = 'hidden';
    clearExcSections();
    let el = document.getElementsByClassName('show-section')
        .item(0);
    el.style.display = 'none';
    categoryVisible = false;
    muscleGroupVisible = false;
}

function clearExcSections() {
    const excSections = document.getElementsByClassName('setter');
    for (let i = 0; i < excSections.length; i++) {
        excSections.item(i).value = '0';
    }
}

function clearDaysSections() {
    const daysSections = document.getElementsByClassName('day');
    document.getElementById('workout-name').value = '';
    let li = document.getElementsByTagName('ol')[0].children;
    for (let e of li) {
        for (const ch of e.children) {
            e.removeChild(ch);
        }
    }
    addAndRemove();
}

function openLog() {
    document.getElementsByClassName('create-workout-log').item(0).style.visibility = 'visible';
    document.getElementsByTagName('body').item(0).style.overflowY = 'hidden';
}

function closeLog() {
    document.getElementsByTagName('body').item(0).style.overflowY = 'visible';
    clearDaysSections();
    clearExcSections();
    closeAllLogs();
    categoryVisible = false;
    muscleGroupVisible = false;
}

function openExercisesLog(id) {
    document.getElementById('exercises-section').style.visibility = 'visible';
    day = document.getElementById(id).value;
    console.log(day);
}


function openMuscleGroupLog() {
    document.getElementsByTagName('body').item(0).style.overflowY = 'hidden';
    document.getElementById('category-section').style.visibility = 'hidden';
    categoryVisible = false;
    if (!muscleGroupVisible) {
        document.getElementById('muscle-group-section').style.visibility = 'visible'
        muscleGroupVisible = true;
    } else {
        document.getElementById('muscle-group-section').style.visibility = 'hidden';
        muscleGroupVisible = false;
    }
}

function openCategoryLog() {
    document.getElementById('muscle-group-section').style.visibility = 'hidden';
    muscleGroupVisible = false;
    if (!categoryVisible) {
        document.getElementById('category-section').style.visibility = 'visible';
        categoryVisible = true;
    } else {
        document.getElementById('category-section').style.visibility = 'hidden';
        categoryVisible = false;
    }
}

function responsive() {
    if (screen.width > 700) {
        let el = document.getElementsByTagName("ul")
            .item(0);
        // el.style.display='flex';
        // el.style.position='static';
        // document.getElementsByClassName('menu').item(0).style.display='none';
    }
}

function openMenu() {
    menuClicks++;
    if (menuClicks % 2 === 1) {
        document.getElementsByTagName("ul").item(0).style.display = 'grid';
    }
    if (menuClicks % 2 === 0) {
        document.getElementsByTagName("ul").item(0).style.display = 'none';
    }
}

function showAll() {
    document.getElementById('muscle-group-section').style.visibility = 'hidden';
    document.getElementById('category-section').style.visibility = 'hidden';
    categoryVisible = false;
    muscleGroupVisible = false;
}

responsive();

function show(id) {
    let el = document.getElementsByClassName('show-section')
        .item(0);
    el.style.display = 'grid';
    let img = document.getElementById('imageid');
    let e = document.querySelectorAll('.show#' + id)[0];
    img.src = e.value;
    console.log(img.src);
    console.log(id);
}

function hide() {
    let el = document.getElementsByClassName('show-section')
        .item(0);
    el.style.display = 'none';
}

function filter(id) {
    let e = document.getElementById(id);
    let ex = e.value;
    let f = e.text;
    ex = ex.filter(e => e.value === f);
}

function addEX(id) {
    let el = document.getElementById('workout-name');
    console.log(el.content);

    console.log(document.getElementById(id).value)
    let value = document.getElementById(id).value.toLowerCase()
        .replaceAll('_', ' ');
    let li = document.querySelector('ol#' + day);
    let add = document.createElement('li');
    add.onclick
        = function () {
        remove(day, value);
        addAndRemove();
    };
    add.id = value;
    add.className = 'remove';
    add.textContent = value;
    li.appendChild(add);
    let ex = value;
    let sets = document.querySelectorAll('.input-div')[0]
        .getElementsByTagName('input')[0].value;
    let kg = document.querySelectorAll('.input-div')[1]
        .getElementsByTagName('input')[0].value;
    let reps = document.querySelectorAll('.input-div')[2]
        .getElementsByTagName('input')[0].value;
    let num = li.children.length;
    let exDay = day;
    addAndRemove();

    let json = {
        'exerciseType': ex,
        'numberOfSets': sets,
        'kg' : kg,
        'reps': reps,
        'num': num,
        'workoutDay': exDay
    }
    let str = JSON.stringify(json)
        str = str.slice(0, 0) + str.slice(1, str.length - 1) + str.slice(str.length, str.length)
    collection.push(json);
    closeExercisesLog();

    console.log(collection)
}

function remove(day, value) {
    let e = collection[0];
    console.log(day + ' ' +  e.workoutDay)
    console.log(value + ' ' + e.exerciseType)
    let ex = collection.filter(e => e.exerciseType == value && e.workoutDay == day)[0];
    console.log(ex);
    let num = ex.num;
    collection = collection.filter(e => e != ex);
    collection.filter(e => e.num > num).forEach(e => e.num -= 1);
    console.log(collection)
    let li = document.querySelectorAll('ol#' + day).item(0);
    let toRemove = document.getElementById(value);
    console.log(toRemove.className)
    li.removeChild(toRemove);
}

function addAndRemove() {
    let days = ['MONDAY', 'TUESDAY', 'WEDNESDAY', 'THURSDAY', 'FRIDAY', 'SATURDAY', 'SUNDAY'];
    for (const d of days) {
        let img = document.querySelector('section.' + d + ' img');
        let li = document.querySelector('ol#' + d);
        if (li.children.length === 0) {
            img.style.display = 'grid';
        } else {
            img.style.display = 'none'
        }
    }
}


function saveWorkout() {
    let el = document.getElementById('workout-name');
    document.getElementById('name').value =
        el.value;
    document.getElementById('current').value = JSON.stringify(collection);
}

window.addEventListener('resize', responsive);
