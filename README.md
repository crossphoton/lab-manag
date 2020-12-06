# lab-management

This repo can be used to host a Lab Management System **REST API**. It can be used in schools/colleges/universities to ease the workflow.


<br>
<br>
<br>
<br>

## User Management

The system uses authentication to do most of tasks.

### Register a user

POST `/api/users`

Available to `Public`

#### JSON Payload

```
{
    username: your_username,
    password: your_password,
    name: your_name
}
```

**Note:** By default a new user is assigned the Student level permission.
Admin can go to firebase dashboard to update permissions.

#### Return values

**Success**

Status <font color="green"> 200</font>

JWT token in body and as a cookie.

Example:

`eyJ0eXAiOiJKV1QihbGciOiJIUzM4NCJ9.eyJyb2xlIjoiUk9MRV9URUFDSEVSIiwiaXNzIjoiY3Jvc3NwaG90b24iLCJ1c2VybmFtZSI6InRlZXIifQ.SxrsxYMv5kFBwOQ-xJTV0MYoSKYIq7l6Y2f-XNcfA4yrEkztF7gtuje4VTZ`

**Failiure**

Error <font color="red">  404 </font>: For already registered `username`

Error <font color="yellow">  500 </font>: For server error

<br>


### Login a user

GET `/api/users`

Available to `Public`

JSON Payload

```
{
    username: your_username,
    password: your_password
}
```

#### Return values

<font color="green"> Success </font>

JWT token in body and as a cookie.

Example:

`eyJ0eXAiOiJKV1QihbGciOiJIUzM4NCJ9.eyJyb2xlIjoiUk9MRV9URUFDSEVSIiwiaXNzIjoiY3Jvc3NwaG90b24iLCJ1c2VybmFtZSI6InRlZXIifQ.SxrsxYMv5kFBwOQ-xJTV0MYoSKYIq7l6Y2f-XNcfA4yrEkztF7gtuje4VTZ`

**Failiure**

Error <font color="red">  404 </font>: For not registered user

Error <font color="red">  405 </font>: For wrong password

Error <font color="yellow">  500 </font>: For server error


<br>
<br>
<br>
<br>





















## Tasks Management

Tasks are created to evaluate students performance. Contains task details, total marks and students' individual marks.

### Creating a task

POST `/api/task`

Available to `Teachers`, `Admin`

JSON Payload

```
{
    title: task_title,
    body: task_body,
    totalMarks: task_totalMarks
}
```

#### Return values

**Success**

Status <font color="green"> 200</font>

**Failiure**

Error <font color="red">  401 </font>: For user not logged in

Error <font color="red">  403 </font>: For action not allowed (User with `student` role trying to create a task)

Error <font color="yellow">  500 </font>: For server error


<br>


### Getting all tasks

GET `/api/task`

Available to `Students`, `Teachers`, `Admin`

JSON Payload

none

#### Return values

**Success**

Status <font color="green"> 200</font>

Example:
```
[
  {
    "g8tzCqmCV5sAv61VaZvp": {
      "owner": "admin",
      "title": "Complete DS work",
      "studentRecord": {
        "crossphoton": 0
      },
      "totalMarks": 100,
      "body": "Hello World"
    }
  },
  {
    "iGSmFtRwimU0FPNj04Bo": {
      "owner": "admin",
      "totalMarks": 100,
      "studentRecord": {
        "crossphoton": 10
      },
      "title": "Complete DS work"
    }
  },
  {
    "ynneZASQVeni3FxNTNzk": {
      "owner": "admin",
      "studentRecord": {
        "crossphoton": 0
      },
      "body": "Hello World",
      "totalMarks": 100,
      "title": "Complete DS work"
    }
  }
]
```

**Failiure**

Error <font color="red">  401 </font>: For user not logged in

Error <font color="yellow">  500 </font>: For server error

<br>

### Getting a particular task

GET `/api/task/{task_id}`

Available to `Students`, `Teachers`, `Admin`

JSON Payload

none

#### Return values

**Success**

Status <font color="green"> 200</font>

Example:
```
{
    "ynneZASQVeni3FxNTNzk": {
      "owner": "admin",
      "studentRecord": {
        "crossphoton": 0
      },
      "body": "Hello World",
      "totalMarks": 100,
      "title": "Complete DS work"
    }
}
```

**Failiure**

Error <font color="red">  401 </font>: For user not logged in

Error <font color="yellow">  500 </font>: For server error

<br>

### Deleting a task

DELETE `/api/task/{task_id}`

Available to `Teachers`, `Admin`

JSON Payload

none

#### Return values

**Success**

Status <font color="green"> 200</font>

**Failiure**

Error <font color="red">  403 </font>: For action not allowed (User with `student` role trying to create a task)

Error <font color="yellow">  500 </font>: For server error


<br>
<br>
<br>
<br>































## Notice Management

Notices can be used by teachers to give updates to students for a given task.


<br>

### Creating an notice

POST `/api/task/{task_id}/notice`

Available to `Teachers`, `Admin`

JSON Payload

```
{
    title: notice_title,
    body: notice_body
}
```

#### Return values

**Success**

Status <font color="green"> 200</font>

**Failiure**

Error <font color="red">  401 </font>: For user not logged in

Error <font color="red">  403 </font>: For action not allowed (User with student role trying to create a task)

Error <font color="yellow">  500 </font>: For server error


<br>


### Getting all notices

GET `/api/task/{task_id}/notice`

Available to `Students`, `Teachers`, `Admin`

JSON Payload

none

#### Return values

**Success**

Status <font color="green"> 200</font>

Example:
```
[
  {
    "0bn1CarJZXfIwnS31mCK": {
      "owner": "teacher",
      "body": "slngslrk",
      "title": "sjj"
    }
  },
  {
    "wlKMbHoA4jYZ6jHRzzya": {
      "owner": "teacher",
      "body": "sljblrbbil",
      "title": "Project sjj"
    }
  }
]
```

**Failiure**

Error <font color="red">  401 </font>: For user not logged in

Error <font color="yellow">  500 </font>: For server error


<br>

### Getting a particular notice

GET `/api/task/{task_id}/notice/{notice_id}`

Available to `Students`, `Teachers`, `Admin`

JSON Payload

none

#### Return values

**Success**

Status <font color="green"> 200</font>

Example:
```
{
    "wlKMbHoA4jYZ6jHRzzya": {
        "owner": "teacher",
        "body": "sljblrbbil",
        "title": "Project sjj"
    }
}
```

**Failiure**

Error <font color="red">  401 </font>: For user not logged in

Error <font color="yellow">  500 </font>: For server error

<br>

### Deleting a notice

DELETE `/api/task/{task_id}/notice/{notice_id}`

Available to `Teachers`, `Admin`

JSON Payload

none

#### Return values

**Success**

Status <font color="green"> 200</font>

**Failiure**

Error <font color="red">  403 </font>: For action not allowed (User with `student` role trying to create a task)

Error <font color="yellow">  500 </font>: For server error


<br>
<br>
<br>
<br>





























## Marks Management

These endpoints can be used by a teacher to update marks of students.

<br>


### Updating marks of a student

POST `/api/task/{task_id}/updateMarks`

Available to `Teachers`, `Admin`

JSON Payload

```
{
    username: student_username,
    marks: marks_to_be_alloted
}
```

#### Return values

**Success**

Status <font color="green"> 200</font>

**Failiure**

Error <font color="red">  401 </font>: For user not logged in

Error <font color="red">  403 </font>: For action not allowed (User with student role trying to create a task)

Error <font color="yellow">  500 </font>: For server error


<br>
<br>
<br>
<br>



















## Announcements Management

Announcements can be used to give updates to students aside from tasks.

### Creating an announcement

POST `/api/announcement`

Available to `Teachers`, `Admin`

JSON Payload

```
{
    title: task_title,
    body: task_body
}
```

#### Return values

**Success**

Status <font color="green"> 200</font>

**Failiure**

Error <font color="red">  401 </font>: For user not logged in

Error <font color="red">  403 </font>: For action not allowed (User with student role trying to create a task)

Error <font color="yellow">  500 </font>: For server error

### Getting all anouncements

GET `/api/announcement`

Available to `Students`, `Teachers`, `Admin`

JSON Payload

none

#### Return values

**Success**

Status <font color="green"> 200</font>

Example:
```
[
  {
    "ivIRncyMa3GxueAXkfTw": {
      "owner": "abc",
      "title": "sglba",
      "body": "sgljblisbliblibt."
    }
  },
  {
    "wIUbf4b8VIQEZ1XNBjsu": {
      "owner": "abc",
      "body": "Test loren ipsum",
      "title": "Test announcement 2"
    }
  }
]
```

**Failiure**

Error <font color="red">  401 </font>: For user not logged in

Error <font color="yellow">  500 </font>: For server error

### Getting particular anouncement

GET `/api/announcement/{announcement_id}`

Available to `Students`, `Teachers`, `Admin`

JSON Payload

none

#### Return values

**Success**

Status <font color="green"> 200</font>

Example:
```
{
"wIUbf4b8VIQEZ1XNBjsu": {
    "owner": "abc",
    "body": "Test loren ipsum",
    "title": "Test announcement 2"
    }
}
```

**Failiure**

Error <font color="red">  401 </font>: For user not logged in

Error <font color="yellow">  500 </font>: For server error

<br>

### Deleting an announcement

DELETE `/api/announcement/{announcement_id}`

Available to `Teachers`, `Admin`

JSON Payload

none

#### Return values

**Success**

Status <font color="green"> 200</font>

**Failiure**

Error <font color="red">  403 </font>: For action not allowed (User with `student` role trying to create a task)

Error <font color="yellow">  500 </font>: For server error