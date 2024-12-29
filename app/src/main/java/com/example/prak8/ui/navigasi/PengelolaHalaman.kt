package com.example.prak8.ui.navigasi

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.prak8.ui.view.DestinasiDetail
import com.example.prak8.ui.view.DestinasiEdit
import com.example.prak8.ui.view.DestinasiEntry
import com.example.prak8.ui.view.DestinasiHome
import com.example.prak8.ui.view.DetailView
import com.example.prak8.ui.view.EditView
import com.example.prak8.ui.view.EntryMhsScreen
import com.example.prak8.ui.view.HomeScreen

@Composable
fun PengelolaHalaman(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = DestinasiHome.route,
        modifier = Modifier
    ) {
        composable(
            route = DestinasiHome.route
        ){
            HomeScreen(
                navigateToItemEntry = { navController.navigate(DestinasiEntry.route) },
                onDetailClick = { nim ->
                    navController.navigate("${DestinasiDetail.route}/$nim")
                    println(nim)
                }
            )
        }
        composable(
            route = DestinasiEntry.route
        ){
            EntryMhsScreen(
                navigateBack = {
                    navController.navigate(DestinasiHome.route) {
                        popUpTo(DestinasiHome.route) { inclusive = true }
                    }
                }
            )
        }
        composable(
            route = DestinasiDetail.routeWithArgs,
            arguments = listOf(navArgument(DestinasiDetail.nim){
                type = NavType.StringType
            })
        ){ backStackEntry ->
            val nim = backStackEntry.arguments?.getString(DestinasiDetail.nim)
            nim?.let {
                DetailView(
                    navigateBack = {
                        navController.navigateUp()
                    },
                    onEditClick = { nim ->
                        navController.navigate("${DestinasiEdit.route}/$nim")
                        println(nim)
                    }
                )
            }
        }
        composable(
            route = DestinasiEdit.routeWithArgs,
            arguments = listOf(navArgument(DestinasiEdit.nim){
                type = NavType.StringType
            })
        ){
            EditView(
                navigateBack = {
                    navController.popBackStack()
                },
                onNavigateUp = {
                    navController.navigate(
                        DestinasiEdit.route
                    ){
                        popUpTo(DestinasiHome.route){
                            inclusive = true
                        }
                    }
                }
            )
        }
    }
}