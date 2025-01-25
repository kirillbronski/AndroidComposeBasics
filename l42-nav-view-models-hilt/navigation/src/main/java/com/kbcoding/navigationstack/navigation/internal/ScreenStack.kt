package com.kbcoding.navigationstack.navigation.internal

import android.os.Parcel
import android.os.Parcelable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.core.os.ParcelCompat
import com.kbcoding.navigationstack.navigation.Route
import com.kbcoding.navigationstack.navigation.Screen
import com.kbcoding.navigationstack.navigation.ScreenResponseReceiver


/**
 * Core navigation stack implementation.
 */
internal class ScreenStack(
    private val routes: SnapshotStateList<RouteRecord>,
    private val screenResponsesBus: ScreenResponsesBus = ScreenResponsesBus()
) : Parcelable {

    val isRoot: Boolean get() = routes.size == 1
    val currentRoute: Route get() = routes.last().route
    val currentUuid: String get() = routes.last().uuid
    val currentScreen: Screen by derivedStateOf {
        currentRoute.screenProducer()
    }
    val screenResponseReceiver: ScreenResponseReceiver = screenResponsesBus

    constructor(routes: List<Route>) : this(
        routes.map(::RouteRecord).toMutableStateList()
    )

    constructor(parcel: Parcel) : this(
        SnapshotStateList<RouteRecord>().also { newList ->
            ParcelCompat.readList(
                parcel,
                newList,
                RouteRecord::class.java.classLoader,
                RouteRecord::class.java,
            )
        }
    )

    constructor(rootRoute: Route) : this(
        routes = mutableStateListOf(RouteRecord(rootRoute))
    )

    fun launch(route: Route) {
        screenResponsesBus.cleanUp()
        routes.add(RouteRecord(route))
    }

    fun pop(response: Any?): RouteRecord? {
        val removedRouteRecord = routes.removeLastOrNull()
        if (removedRouteRecord != null) {
            if (response != null) {
                screenResponsesBus.send(response)
            }
        }
        return removedRouteRecord
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeList(routes)
    }

    override fun describeContents(): Int {
        return 0
    }

    fun getAllRouteRecords(): List<RouteRecord> = routes

    companion object CREATOR : Parcelable.Creator<ScreenStack> {
        override fun createFromParcel(parcel: Parcel): ScreenStack {
            return ScreenStack(parcel)
        }

        override fun newArray(size: Int): Array<ScreenStack?> {
            return arrayOfNulls(size)
        }
    }

}