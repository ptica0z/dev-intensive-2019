
package ru.skillbranch.devintensive.models

class Bender(
    var status: Status = Status.NORMAL,
    var question: Question = Question.NAME) {

    fun askQuestion():String= when(question){
        Question.NAME -> Question.NAME.question
        Question.PROFESSION -> Question.PROFESSION.question
        Question.MATERIAL -> Question.MATERIAL.question
        Question.BDAY -> Question.BDAY.question
        Question.SERIAL -> Question.SERIAL.question
        Question.IDLE -> Question.IDLE.question
    }

    fun listnAnswer(ansver: String): Pair<String, Triple<Int, Int, Int>>{
        if(question.equals(Question.IDLE))
            return question.question to status.color
        val formatError = cheсkAswerFormat(question, ansver)
        if(formatError.isNullOrEmpty()) {
            if(question.answers.contains(ansver.toLowerCase())) {
                question = question.nextQuestion()
                return "Отлично - это правильный ответ!\n${question.question}" to status.color
            } else {
                if(status == Status.CRITICAL){
                    question = Question.NAME
                    status = status.nextStatuse()
                    return "Это неправильный ответ. Давай все по новой\n ${question.question}" to status.color
                }else {
                    status = status.nextStatuse()
                    return "Это не правильный ответ!\n${question.question}" to status.color
                }
            }
        }
        else
            return formatError + "\n${question.question}" to status.color
    }

    //Функция проверяет только формат ответа
    fun cheсkAswerFormat(question: Question, answer: String): String?{
        return when(question){
            Question.NAME -> {
                if("^[a-zа-я]".toRegex().containsMatchIn(answer))
                    "Имя должно начинаться с заглавной буквы"
                else null
            }
            Question.PROFESSION -> {
                if("^[A-ZА-Я]".toRegex().containsMatchIn(answer))
                    "Профессия должна начинаться со строчной буквы"
                else null
            }
            Question.MATERIAL -> {
                if("[\\d]".toRegex().containsMatchIn(answer))
                    "Материал не должен содержать цифр"
                else null
            }
            Question.BDAY -> {
                if("[\\D]".toRegex().containsMatchIn(answer))
                    "Год моего рождения должен содержать только цифры"
                else null
            }
            Question.SERIAL ->{
                if(!"[\\d]{7}".toRegex().matches(answer))
                    "Серийный номер содержит только цифры, и их 7"
                else null
            }
            Question.IDLE -> ""
        }
    }

    enum class Status(val color: Triple<Int, Int, Int>){
        NORMAL(Triple(255, 255, 255)) ,
        WARNING(Triple(255, 120, 0)),
        DANGER(Triple(255, 60, 60)),
        CRITICAL(Triple(255, 0, 0)) ;

        fun nextStatuse(): Status{
            return if(this.ordinal<values().lastIndex){
                values()[this.ordinal + 1]
            } else{
                values()[0]
            }
        }
    }

    enum class Question(val question: String, val answers: List<String>) {
        NAME("Как меня зовут?", listOf("бендер", "bender")){
            override fun nextQuestion(): Question = PROFESSION
        },
        PROFESSION("Назови мою профессию?", listOf("сгибальщик", "bender")){
            override fun nextQuestion(): Question = MATERIAL
        },
        MATERIAL("Из чего я сделан?", listOf("металл", "дерево", "metal", "iron", "wood")){
            override fun nextQuestion(): Question = BDAY
        },
        BDAY("Когда меня создали?", listOf("2993")){
            override fun nextQuestion(): Question = SERIAL
        },
        SERIAL("Мой серийный номер?", listOf("2716057")){
            override fun nextQuestion(): Question = IDLE
        },
        IDLE("На этом все, вопросов больше нет", listOf()){
            override fun nextQuestion(): Question = IDLE
        };

        abstract fun nextQuestion(): Question
    }

}