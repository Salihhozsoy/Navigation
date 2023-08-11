package com.example.navigationfirstproject

sealed class AdapterState {
    object Idle:AdapterState()
    class Removed(val index:Int):AdapterState()
    class Changed(val index:Int):AdapterState()
    class Added(val index:Int):AdapterState()
}
