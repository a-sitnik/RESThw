# RESThw
HelloWorld RESTful application on Spring Fw

#### Responds on:
* http://localhost:8080/schedule - not very REST-specific construction
* http://localhost:8080/lectors - id, name
* http://localhost:8080/subjects - id, name

#### Database
* SQLite, database sample included
* Structure: Lectors < Lec2Subj > Subjects,  Lec2Subj - TimeTable, where "<" one2many, "-" one2one
