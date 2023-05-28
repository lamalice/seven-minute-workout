package com.example.sevenminuteworkout

class ExerciseModel (
    private var id :Int,
    private var name: String,
    private var image: Int,
    private var isCompleted: Boolean = false,
    private var isSelected: Boolean = false
    ){

    fun getId(): Int {
        return id
    }

    fun setId(id : Int){
        this.id = id
    }

    fun getName(): String {
        return name
    }

    fun setName(name: String){
        this.name = name
    }

    fun getImage(): Int {
        return image
    }

    fun setImage(image: Int){
        this.image = image
    }

    fun setIsCompleted(): Boolean{
        return isCompleted
    }

    fun getIsCompleted(isCompleted: Boolean){
        this.isCompleted = isCompleted
    }

    fun getIsSelected():Boolean{
        return isSelected
    }

    fun setIsSelected(isCompleted: Boolean){
        this.isSelected = isSelected
    }
}


