import React from "react"
import PropTypes from "prop-types"

import Student from "./Student"

const ListStudent = (student) => {

    const renderStudent = () => {
        return student.map((student, i) => (
            <li key={i} className="ListSelectLi" title="ListSelect-Li">
                <Student student={student} />
            </li>
        ))
    }

    return (
        student.length === 0 ? <div><span>Empty</span></div> : <ul>{renderStudent()}</ul>
    )

}

ListStudent.propTypes = {
    mercs: PropTypes.array.isRequired
}

export default ListStudent