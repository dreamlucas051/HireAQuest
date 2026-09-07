package com.example

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.viewmodel.QuestViewModel

@Composable
fun HireaquestNavigation(viewModel: QuestViewModel) {
    val navController = rememberNavController()
    
    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            LoginScreen(onLoginSuccess = { username ->
                if (username.equals("Greg", ignoreCase = true)) {
                    navController.navigate("quest_board") {
                        popUpTo("login") { inclusive = true }
                    }
                } else {
                    navController.navigate("home") {
                        popUpTo("login") { inclusive = true }
                    }
                }
            })
        }
        
        composable("home") {
            HomeModeScreen(
                onHireSelected = {
                    navController.navigate("select_person")
                },
                onBecomeQuesterSelected = {
                    navController.navigate("become_quester")
                }
            )
        }
        
        composable("select_person") {
            SelectPersonScreen(
                onPersonSelected = { personName ->
                    navController.navigate("hire_tier/$personName")
                },
                onBack = { navController.popBackStack() }
            )
        }

        composable("hire_tier/{personName}") { backStackEntry ->
            val personName = backStackEntry.arguments?.getString("personName") ?: "Someone"
            HireTierScreen(
                personName = personName,
                onTierSelected = { tier ->
                    navController.navigate("create_task/$personName/$tier")
                },
                onBack = { navController.popBackStack() }
            )
        }
        
        composable("create_task/{personName}/{tier}") { backStackEntry ->
            val personName = backStackEntry.arguments?.getString("personName") ?: "Someone"
            val tier = backStackEntry.arguments?.getString("tier") ?: "HireX"
            CreateTaskScreen(
                personName = personName,
                tier = tier,
                viewModel = viewModel,
                onTaskPosted = {
                    navController.navigate("quest_board") {
                        popUpTo("home")
                    }
                },
                onBack = { navController.popBackStack() }
            )
        }
        
        composable("become_quester") {
            BecomeQuesterScreen(
                onSubmit = {
                    navController.navigate("quest_board") {
                        popUpTo("home")
                    }
                },
                onBack = { navController.popBackStack() }
            )
        }
        
        composable("quest_board") {
            QuestBoardScreen(
                viewModel = viewModel,
                onBack = { navController.popBackStack() }
            )
        }
    }
}
