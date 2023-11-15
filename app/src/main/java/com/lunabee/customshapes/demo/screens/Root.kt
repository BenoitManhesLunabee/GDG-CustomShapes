package com.lunabee.customshapes.demo.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun Root(
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        navController = navController,
        startDestination = HomeScreenRoute,
    ) {
        composable(HomeScreenRoute) {
            HomeScreen(
                navigateToPolygon = {
                    navController.navigate(PolygonScreenRoute)
                },
                navigateToBasicElliptic = {
                    navController.navigate(BottomEllipticScreenRoute)
                },
                navigateToSvg = {
                    navController.navigate(SvgScreenRoute)
                },
                navigateToCutout = {
                    navController.navigate(CutoutScreenRoute)
                },
                navigateToGraphicsShapes = {
                    navController.navigate(GraphicsShapesScreenRoute)
                },
                navigateToAnimation = {
                    navController.navigate(AnimationsScreenRoute)
                }
            )
        }

        composable(PolygonScreenRoute) {
            PolygonScreen(
                onBackPressed = { navController.popBackStack() },
            )
        }

        composable(BottomEllipticScreenRoute) {
            BottomEllipticScreen(
                onBackPressed = { navController.popBackStack() },
            )
        }

        composable(SvgScreenRoute) {
            SvgScreen(
                onBackPressed = { navController.popBackStack() },
            )
        }

        composable(CutoutScreenRoute) {
            CutoutScreen(
                onBackPressed = { navController.popBackStack() },
            )
        }

        composable(GraphicsShapesScreenRoute) {
            MorphingScreen(
                onBackPressed = { navController.popBackStack() },
            )
        }

        composable(AnimationsScreenRoute) {
            AnimationsScreen(
                onBackPressed = { navController.popBackStack() },
            )
        }
    }
}