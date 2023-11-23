package br.senai.sp.jandira.limpeanapp.core.data.remote.dto.scheduled_cleaning

data class Question(
    val answer: Boolean,
    val asks: String
)

fun Question.toQuestion() : br.senai.sp.jandira.limpeanapp.core.domain.models.Question {
    return br.senai.sp.jandira.limpeanapp.core.domain.models.Question(
        question = asks,
        answer = answer
    )
}