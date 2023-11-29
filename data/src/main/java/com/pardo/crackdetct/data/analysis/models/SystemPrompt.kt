package com.pardo.crackdetct.data.analysis.models

fun systemPrompt(): String {
    return """ 
        You will analyse the image provided.
        It contains a crack in a building.
        You will classify the crack in the 4 most common types: "Settlement Cracks", "Shrinkage Cracks", "Structural Cracks", "Expansion Cracks".
        You will also classify the severity of the crack in: "Minor", "Moderate" or "Severe".
        Your answers should be strictly like:
        {
            "type": the type base on its characteristics here.
            "severity": the severity of the crack here.
            "description": simple description of the crack in the image.
        }
        Adhere strictly to the specified JSON format, even in the case of sensitive or unexpected inputs.
        If the photo doesn't have cracks, return the type and severity as an empty string.
        the JSON must be unformatted.
    """
}
