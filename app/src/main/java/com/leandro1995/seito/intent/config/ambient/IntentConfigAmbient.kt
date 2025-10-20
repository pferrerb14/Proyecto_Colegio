package com.leandro1995.seito.intent.config.ambient

abstract class IntentConfigAmbient<AE> {

    abstract fun initConfig(event: AE?)
}