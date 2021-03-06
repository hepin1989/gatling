/*
 * Copyright 2011-2018 GatlingCorp (http://gatling.io)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.gatling.http.action.async.sse

import io.gatling.http.action.async.AsyncTx
import io.gatling.http.engine.HttpEngine

import akka.actor.ActorRef

object SseTx {

  def start(tx: AsyncTx, sseActor: ActorRef, httpEngine: HttpEngine): Unit = {
    val handler = new SseListener(tx, sseActor)
    httpEngine.httpClient.sendRequest(tx.request, tx.session.userId, tx.protocol.enginePart.shareConnections, handler)
  }
}
