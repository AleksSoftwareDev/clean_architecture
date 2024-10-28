import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.ethermail.androidchallenge.presentation.assets.AssetsScreen
import com.ethermail.androidchallenge.presentation.markets.MarketScreen

@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "assets") {
        composable("assets") {
            AssetsScreen(onNavigateToMarket = { marketId ->
                navController.navigate("market/$marketId")
            })
        }
        composable(
            "market/{marketId}",
            arguments = listOf(navArgument("marketId") { type = NavType.StringType })
        ) { backStackEntry ->
            val marketId = backStackEntry.arguments?.getString("marketId") ?: return@composable
            MarketScreen(marketId = marketId)
        }
    }
}
