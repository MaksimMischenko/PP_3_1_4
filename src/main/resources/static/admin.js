const url1 ='http://localhost:8080/users/user'

const url2 ='http://localhost:8080/users'

const url3 ='http://localhost:8080/users/'

//Nav Bar

fetch(url1)

    .then(res => { res.json().then(

        user=>{

            let navBar = ""

            navBar += "<b class=\"text-white\">"+user.email+"</b>"

            navBar += "<span class=\"text-white\"> with roles: </span>"

            navBar += "<span class=\"text-white\">"

            user.roles.forEach((role) => navBar += role.role.replace('ROLE_','')+' ')

            navBar += "</span>"

            document.getElementById("navBar").innerHTML = navBar

        }

    )

    })

//Admin Panel

const showTable = (users) => {

    let table = document.getElementById("tableAllUsers").innerHTML

    users.forEach((user)=> {

        table += `

                <tr id="${user.id}">

                    <td>${user.id}</td>

                    <td>${user.name}</td>

                    <td>${user.surname}</td>

                    <td>${user.email}</td>

                    <td>${user.phone}</td>

                    <td>`

        user.roles.forEach((role) => table += role.role.replace('ROLE_','')+" ")

        table += `

                    </td>

                    <td><button class="btn btn-info eBtn" data-toggle="modal">Edit</button></td>

                    <td><button class="btn btn-danger dBtn" data-toggle="modal">Delete</button></td>

                 `

    })

    document.getElementById("tableAllUsers").innerHTML = table

}



fetch(url2)

    .then( response => response.json())

    .then(data => showTable(data))



const on = (element, event, selector, handler) => {

    element.addEventListener(event, e => {

        if(e.target.closest(selector)){

            handler(e)

        }

    })

}



//Add new user

newUserLink.addEventListener('click', (e) => {

    name.value = ''

    surname.value = ''

    email.value = ''

    phone.value = ''

    pass.value = ''

    roles.value = null

})



newUserForm.addEventListener('submit', (e) => {

    e.preventDefault()

    let id = 0

    let rolesList = [];

    for(let i = 0; i < $('#roles').val().length; i++){

        if ($('#roles').val()[i]==='ROLE_ADMIN') {

            id=1

        } else {

            id=2

        }

        rolesList[i] = {id: id, role: $('#roles').val()[i]} ;

    }

    let newUser = {

        name: name.value,

        surname: surname.value,

        email: email.value,

        phone: phone.value,

        password: pass.value,

        roles: rolesList

    }

    fetch(url2, {

        method: 'POST',

        headers: {

            'Content-Type': 'application/json'

        },

        body: JSON.stringify(newUser)

    })

        .then(response => response.json())

        .then((data) => {

            const newUserInTable = []

            newUserInTable.push(data)

            showTable(newUserInTable)

        })

        .then(() => document.getElementById('userTable').click())

})



//Edit Modal



on(document, 'click', '.eBtn', e => {

    const lineEdit = e.target.parentNode.parentNode

    const idEditModal = lineEdit.children[0].innerHTML

    const nameEditModal = lineEdit.children[1].innerHTML

    const surnameEditModal = lineEdit.children[2].innerHTML

    const emailEditModal = lineEdit.children[3].innerHTML

    const phoneEditModal = lineEdit.children[4].innerHTML



    idEdit.value = idEditModal

    nameEdit.value = nameEditModal

    surnameEdit.value = surnameEditModal

    emailEdit.value = emailEditModal

    phoneEdit.value = phoneEditModal

    $('#editModal').modal()

})



editModal.addEventListener('submit', (e) => {

    e.preventDefault()

    let id = 0

    let rolesListEdit = [];

    for(let i = 0; i < $('#rolesEdit').val().length; i++){

        if ($('#rolesEdit').val()[i]==='ROLE_ADMIN') {

            id=1

        } else {

            id=2

        }

        rolesListEdit[i] = {id: id, role: $('#rolesEdit').val()[i]} ;

    }

    let editUser = {

        id: idEdit.value,

        name: nameEdit.value,

        surname: surnameEdit.value,

        email: emailEdit.value,

        phone: phoneEdit.value,

        password: passEdit.value,

        roles: rolesListEdit

    }

    fetch(url2, {

        method: 'PUT',

        headers: {

            'Content-Type':'application/json'

        },

        body: JSON.stringify(editUser)

    })

        .then(response => response.json())

        .then((data) => {

            const editUserInTable = []

            editUserInTable.push(data)

            showTable(editUserInTable)

        })

        .then(() => document.getElementById(idEdit.value).remove())

        .then(()=> document.getElementById('editModalClose').click())

})



//Delete Modal

on(document, 'click', '.dBtn', e => {

    const lineDelete = e.target.parentNode.parentNode

    const idDeleteModal = lineDelete.children[0].innerHTML

    const nameDeleteModal = lineDelete.children[1].innerHTML

    const surnameDeleteModal = lineDelete.children[2].innerHTML

    const emailDeleteModal = lineDelete.children[3].innerHTML

    const phoneDeleteModal = lineDelete.children[3].innerHTML

    idDelete.value = idDeleteModal

    nameDelete.value = nameDeleteModal

    surnameDelete.value = surnameDeleteModal

    emailDelete.value = emailDeleteModal

    phoneDelete.value = phoneDeleteModal

    $('#deleteModal').modal()

})



deleteModal.addEventListener('submit', (e) => {

    e.preventDefault()

    fetch(url3+idDelete.value, {

        method: 'DELETE'

    })

        .then(() => document.getElementById(idDelete.value).remove())

        .then(()=> document.getElementById('deleteModalClose').click())

})