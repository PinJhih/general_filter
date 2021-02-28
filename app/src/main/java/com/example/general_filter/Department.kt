package com.example.general_filter

data class Department(
    var schoolName: String = "",
    var departmentName: String = "",
    var code: String = "",
    var enrollmentQuota: String = "",
    var genderRequirement: String = "",
    var examQuota: String = "",
    var aboriginalQuota: String = "",
    var offshoreIslandsQuota: String = "",
    var planQuota: String = "",
    var registrationFee: String = "",
    var examDate: String = "",
    var category: String = "",
    var technicalExam: String = "",
    var helpingMeasures: String = "",
    var pinned:Boolean = false
)