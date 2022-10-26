/*
 * This file is generated by jOOQ.
 */
package land.tbp.jooq.keys


import land.tbp.jooq.tables.FlywaySchemaHistory
import land.tbp.jooq.tables.Oauth2token
import land.tbp.jooq.tables.Subscription
import land.tbp.jooq.tables.User
import land.tbp.jooq.tables.YoutubeChannel
import land.tbp.jooq.tables.records.FlywaySchemaHistoryRecord
import land.tbp.jooq.tables.records.Oauth2tokenRecord
import land.tbp.jooq.tables.records.SubscriptionRecord
import land.tbp.jooq.tables.records.UserRecord
import land.tbp.jooq.tables.records.YoutubeChannelRecord

import org.jooq.ForeignKey
import org.jooq.UniqueKey
import org.jooq.impl.DSL
import org.jooq.impl.Internal



// -------------------------------------------------------------------------
// UNIQUE and PRIMARY KEY definitions
// -------------------------------------------------------------------------

val FLYWAY_SCHEMA_HISTORY__PK_FLYWAY_SCHEMA_HISTORY: UniqueKey<FlywaySchemaHistoryRecord> = Internal.createUniqueKey(FlywaySchemaHistory.FLYWAY_SCHEMA_HISTORY, DSL.name("pk_flyway_schema_history"), arrayOf(FlywaySchemaHistory.FLYWAY_SCHEMA_HISTORY.INSTALLED_RANK), true)
val OAUTH2TOKEN__PK_OAUTH2TOKEN: UniqueKey<Oauth2tokenRecord> = Internal.createUniqueKey(Oauth2token.OAUTH2TOKEN, DSL.name("pk_OAuth2Token"), arrayOf(Oauth2token.OAUTH2TOKEN.OAUTH2TOKEN_ID), true)
val SUBSCRIPTION__SUBSCRIPTION_PK: UniqueKey<SubscriptionRecord> = Internal.createUniqueKey(Subscription.SUBSCRIPTION, DSL.name("subscription_pk"), arrayOf(Subscription.SUBSCRIPTION.USER_ID, Subscription.SUBSCRIPTION.YOUTUBE_CHANNEL_ID), true)
val USER__PK_USER: UniqueKey<UserRecord> = Internal.createUniqueKey(User.USER, DSL.name("pk_user"), arrayOf(User.USER.USER_ID), true)
val YOUTUBE_CHANNEL__PK_YOUTUBE_CHANNEL: UniqueKey<YoutubeChannelRecord> = Internal.createUniqueKey(YoutubeChannel.YOUTUBE_CHANNEL, DSL.name("pk_youtube_channel"), arrayOf(YoutubeChannel.YOUTUBE_CHANNEL.YOUTUBE_CHANNEL_ID), true)

// -------------------------------------------------------------------------
// FOREIGN KEY definitions
// -------------------------------------------------------------------------

val OAUTH2TOKEN__FK_OAUTH2TOKEN_PK_USER: ForeignKey<Oauth2tokenRecord, UserRecord> = Internal.createForeignKey(Oauth2token.OAUTH2TOKEN, DSL.name("fk_OAuth2Token_pk_user"), arrayOf(Oauth2token.OAUTH2TOKEN.USER_ID), land.tbp.jooq.keys.USER__PK_USER, arrayOf(User.USER.USER_ID), true)
val SUBSCRIPTION__FK_SUBSCRIPTION_PK_USER: ForeignKey<SubscriptionRecord, UserRecord> = Internal.createForeignKey(Subscription.SUBSCRIPTION, DSL.name("fk_subscription_pk_user"), arrayOf(Subscription.SUBSCRIPTION.USER_ID), land.tbp.jooq.keys.USER__PK_USER, arrayOf(User.USER.USER_ID), true)
val SUBSCRIPTION__FK_SUBSCRIPTION_PK_YOUTUBE_CHANNEL: ForeignKey<SubscriptionRecord, YoutubeChannelRecord> = Internal.createForeignKey(Subscription.SUBSCRIPTION, DSL.name("fk_subscription_pk_youtube_channel"), arrayOf(Subscription.SUBSCRIPTION.YOUTUBE_CHANNEL_ID), land.tbp.jooq.keys.YOUTUBE_CHANNEL__PK_YOUTUBE_CHANNEL, arrayOf(YoutubeChannel.YOUTUBE_CHANNEL.YOUTUBE_CHANNEL_ID), true)