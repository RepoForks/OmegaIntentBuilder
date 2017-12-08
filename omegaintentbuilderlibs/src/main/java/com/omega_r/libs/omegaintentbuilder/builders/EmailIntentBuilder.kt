/*
 * Copyright (c) 2017 Omega-r
 *
 * OmegaIntentBuilder
 * EmailIntentBuilder.kt
 *
 * Author: Roman Tcaregorodtcev  <roman.tc@omega-r.com>
 * Github: R12rus
 * Date:   December 8, 2017
 */
package com.omega_r.libs.omegaintentbuilder.builders

import android.content.Intent
import android.net.Uri
import com.omega_r.libs.omegaintentbuilder.IntentBuilder
import com.omega_r.libs.omegaintentbuilder.OmegaIntentBuilder

class EmailIntentBuilder internal constructor(private val omegaIntentBuilder: OmegaIntentBuilder): BaseShareBuilder<EmailIntentBuilder>(), IntentBuilder {

  /**
   * This method could call ActivityNotFoundException
   *
   * @return Intent for email
   */
  override fun createIntent(): Intent {
    val intent = super.createIntent()
    intent.action = Intent.ACTION_SENDTO
    intent.data = Uri.parse("mailto:")

    return intent
  }

}