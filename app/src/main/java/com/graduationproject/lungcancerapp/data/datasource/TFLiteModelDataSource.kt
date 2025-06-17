package com.graduationproject.lungcancerapp.data.datasource

import android.content.Context
import com.graduationproject.lungcancerapp.data.model.InputFeatures
import com.graduationproject.lungcancerapp.data.model.PredictionResult
import org.tensorflow.lite.Interpreter
import java.io.FileInputStream
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.MappedByteBuffer
import java.nio.channels.FileChannel

class TFLiteModelDataSource(
    context: Context,
    modelPath: String
) : ModelDataSource {

    private val interpreter: Interpreter

    init {
        interpreter = Interpreter(loadModelFile(context, modelPath))
    }

    private fun loadModelFile(context: Context, modelPath: String): MappedByteBuffer {
        val fileDescriptor = context.assets.openFd(modelPath)
        val inputStream = FileInputStream(fileDescriptor.fileDescriptor)
        val fileChannel = inputStream.channel
        val startOffset = fileDescriptor.startOffset
        val declaredLength = fileDescriptor.declaredLength
        return fileChannel.map(FileChannel.MapMode.READ_ONLY, startOffset, declaredLength)
    }

    override fun predict(inputFeatures: InputFeatures): PredictionResult {
        // Convert input features to FloatArray
        val inputs = floatArrayOf(
            inputFeatures.yellowFingers,
            inputFeatures.anxiety,
            inputFeatures.peerPressure,
            inputFeatures.chronicDisease,
            inputFeatures.fatigue,
            inputFeatures.allergy,
            inputFeatures.wheezing,
            inputFeatures.alcoholConsuming,
            inputFeatures.coughing,
            inputFeatures.shortnessOfBreath,
            inputFeatures.swallowingDifficulty,
            inputFeatures.chestPain
        )

        // Input tensor: [1, 12]
        val inputBuffer = ByteBuffer.allocateDirect(12 * 4).apply {
            order(ByteOrder.nativeOrder())
            inputs.forEach { putFloat(it) }
        }

        // Output tensor: [1, 1]
        val outputBuffer = ByteBuffer.allocateDirect(1 * 4).apply {
            order(ByteOrder.nativeOrder())
        }

        // Run inference
        interpreter.run(inputBuffer, outputBuffer)

        // Get output
        outputBuffer.rewind()
        val probability = outputBuffer.float
        return PredictionResult(probability, probability >= 0.5f)
    }

    override fun close() {
        interpreter.close()
    }
}