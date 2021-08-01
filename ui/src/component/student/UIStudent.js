import React, { useEffect } from "react"
import { connect } from "react-redux"

import ListStudent from "./ListStudent"
import { getAllStudentsActionCreator, insertStudentActionCreator } from "./../../action/studentAction"

const UIStudent = ({ students, getStudents, insertStudent }) => {

    console.log(students)

    useEffect(getStudents, [])

    const onSubmit = (event) => {
        const { nickname, legalAge } = event.target.elements
        insertStudent({ nickname: nickname.value, legalAge: legalAge.value })
        nickname.value = null
        legalAge.value = null
        event.preventDefault()
    }

    return (
        <div className="marginWrapper">
            <div className="jobInput">
                <form onSubmit={onSubmit}>
                    <input id="example" type="text" name="text"></input>
                    <input id="example" type="text" name="text"></input>
                    <input type="submit" value="Create merc" />
                </form>
            </div>
            <ListStudent students={students} />
        </div>
    )
}

const mapStateToProps = state => ({
    students: state.students
})

const mapDispatchToProps = dispatch => ({
    getStudents: () => dispatch(getAllStudentsActionCreator()),
    insertStudent: studentInserted => dispatch(insertStudentActionCreator(studentInserted))
})

export default connect(mapStateToProps, mapDispatchToProps)(UIStudent)