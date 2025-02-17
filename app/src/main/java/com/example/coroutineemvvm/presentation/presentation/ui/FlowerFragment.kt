import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.example.coroutineemvvm.presentation.domain.interactor.FlowerInteractor
import com.example.coroutineemvvm.presentation.presentation.ui.PlantsListViewModel
import com.example.coroutineemvvm.presentation.utils.Injector

class FlowerFragment : Fragment() {

    private val viewModel: PlantsListViewModel by viewModels {
        Injector.providePlantListViewModelFactory(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        val binding = FragmentPlantListBinding.inflate(inflater, container, false)
//        context ?: return binding.root

        // show the spinner when [spinner] is true
        viewModel.spinner.observe(viewLifecycleOwner) { show ->
            //            binding.spinner.visibility = if (show) View.VISIBLE else View.GONE
        }

        // Show a snackbar whenever the [snackbar] is updated a non-null value
        viewModel.snackbar.observe(viewLifecycleOwner) { text ->
            text?.let {
                //                Snackbar.make(binding.root, text, Snackbar.LENGTH_SHORT).show()
                viewModel.onSnackbarShown()
            }
        }

//        val adapter = PlantAdapter()
//        binding.plantList.adapter = adapter
//        subscribeUi(adapter)

        setHasOptionsMenu(true)
        return view
    }

//    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
//        inflater.inflate(R.menu.menu_plant_list, menu)
//    }

//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        return when (item.itemId) {
//            R.id.filter_zone -> {
//                updateData()
//                true
//            }
//            else -> super.onOptionsItemSelected(item)
//        }
//    }

//    private fun subscribeUi(adapter: PlantAdapter) {
//        viewModel.plants.observe(viewLifecycleOwner) { plants ->
//            adapter.submitList(plants)
//        }
//    }

//    private fun updateData() {
//        with(viewModel) {
//            if (isFiltered()) {
//                clearGrowZoneNumber()
//            } else {
//                setGrowZoneNumber(9)
//            }
//        }
//    }
}

/**
 * Factory for creating a [PlantListViewModel] with a constructor that takes a [PlantRepository].
 */
class PlantListViewModelFactory(
    private val interactor: FlowerInteractor
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>) = PlantsListViewModel(interactor) as T
}


