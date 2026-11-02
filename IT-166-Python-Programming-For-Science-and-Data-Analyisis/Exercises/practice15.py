import pandas as pd
import matplotlib.pyplot as plt

class_a = pd.DataFrame({
    'student': ['Alice', 'Bob'],
    'Math': [90, 85],
    'Python': [95, 88]
})
class_b = pd.DataFrame({
    'student': ['Carol', 'David'],
    'Math': [92, 78],
    'Python': [89, 84]
})

# 1. Combine vertically
combined = pd.concat([class_a, class_b], ignore_index=True)
# 2. Wide -> long
long_df = pd.melt(
    combined,
    id_vars="student",
    var_name="subject",
    value_name="score",
)
# 4. Long -> wide
wide_df = long_df.pivot(index="student", columns="subject", values="score")

print(f"Combined DataFrame:\n{combined}")
print(f"\nLong-format DataFrame:\n{long_df}")
print(f"\nPivoted back to wide format:\n{wide_df}")

#question 2
days = [1, 2, 3, 4, 5]
city_a = [20, 22, 21, 25, 27]
city_b = [18, 19, 21, 22, 24]

plt.plot(days, city_a, color="blue", linestyle="-", marker="o", label="City A")
plt.plot(days, city_b, color="red", linestyle="--", marker="s", label="City B")
plt.title("Daily Temperature Comparison")
plt.xlabel("Day")
plt.ylabel("Temperature (C)")
plt.legend()
plt.grid(True)
plt.ylim(15, 30)
plt.savefig("temperature_comparison.png", dpi=300)
plt.show()
